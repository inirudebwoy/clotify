(ns clotify.core
  (:require [liberator.core :refer [resource defresource]]
            [ring.middleware.params :refer [wrap-params]]
            [compojure.core :refer [defroutes context GET POST]]))

;; routes
;; GET all tracks in current playlist
(defresource playlist [id]
  :available-media-types ["application/json"]
  :handle-ok (format "Track list for playlist: %s" id)
  :authorized? (fn [_] "Not authorized"))

;; POST new track to end of playlist
(defresource playlist-add []
  :allowed-methods [:post]
  :available-media-types ["application/json"]
  :handle-ok "You have added track to playlist")

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
