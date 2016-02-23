(ns clotify.core
  (:require [clotify.playlist :refer [tracks add-track]]
            [liberator.core :refer [resource defresource]]
            [liberator.representation :refer [as-response]]
            [ring.middleware.params :refer [wrap-params]]
            [compojure.core :refer [defroutes context GET POST]]))

;; retrieve tracks in form of map and manipulate it maybe?
(defn get-playlist-tracks [id] (tracks id))

(defn add-playlist-track [track] (add-track track))

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
  :as-response (fn [d ctx] ; modyfing response
                 (-> (as-response d ctx)
                     (assoc-in [:headers "X-Response-Type"] "SUCCESS")
                     (assoc :body (add-playlist-track ((ctx :request) :params))))))

(defroutes app
  (context "/playlist" []
           (POST "/" [] (fn [_] (playlist-add)))
           (GET "/:id" [id] (playlist id))))

(def handler
  (-> app
      wrap-params))
