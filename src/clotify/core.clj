(ns clotify.core
  (:require [liberator.core :refer [resource defresource]]
            [liberator.representation :refer [as-response]]
            [ring.middleware.params :refer [wrap-params]]
            [compojure.core :refer [defroutes context GET POST]]))


(defn get-playlist-tracks [id] (format "Track list for playlist: %s" id))


;;; routes
;; GET all tracks in current playlist
(defresource playlist [id]
  :available-media-types ["application/json"]
  :handle-ok (get-playlist-tracks id))

;; POST new track to end of playlist
;; TODO: response status should be based on if track could be added
(defresource playlist-add []
  :allowed-methods [:post]
  :available-media-types ["application/json"]
  :handle-ok "You have added track to playlist"
  :as-response (fn [d ctx]
                 (-> (as-response d ctx)
                     (assoc-in [:headers "X-Response-Type"] "SUCCESS"))))

;; POST skip currently running track
(defresource playlist-skip []
  :allowed-methods [:post]
  :available-media-types ["application/json"]
  :handle-ok "You have skipped a track.")

(defroutes app
  (context "/playlist" []
           (POST "/" [] (fn [_] (playlist-add)))
           (GET "/:id" [id] (playlist id))
           (POST "/skip" [] (playlist-skip))))

(def handler
  (-> app
      wrap-params))
