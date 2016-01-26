(ns clotify.core
  (:require [liberator.core :refer [resource defresource]]
            [ring.middleware.params :refer [wrap-params]]
            [compojure.core :refer [defroutes context GET POST]]))

;; routes
;; GET current track
;; GET all tracks in current playlist
;; POST new track to end of playlist
;; POST skip currently running track

(defroutes app
  (context "/playlist" []
  (GET "/" [] (resource :available-media-types ["text/html"]
                        :handle-ok "<html>Hola</html>"))
  (GET "/all" [] (str "this is list of all tracks"))
  (POST "/" [] (str "you have added new track to list"))
  (POST "/skip" [] (str "you have skipped a track"))))

(def handler
  (-> app
      wrap-params))
