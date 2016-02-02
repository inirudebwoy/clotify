(ns clotify.spotify
  (:require [clojure.data.json :as json]))

(def spotify-playlist-tracks-stub "{\"a\":1,\"b\":2}")

(def spotify-skip-track )

;; fetch tracks from spotify, parse into map (json/read-str)
(defn playlist-tracks [id] spotify-playlist-tracks-stub)
