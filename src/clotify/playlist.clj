(ns clotify.playlist
  (:require [cheshire.core :refer :all]))

;; mocks
(def spotify-playlist-tracks-stub (parse-string "{\n  \"href\" : \"https://api.spotify.com/v1/users/jmperezperez/playlists/3cEYpjA9oz9GiPac4AsH4n/tracks?offset=0&limit=100&market=ES\",\n  \"items\" : [ {\n    \"added_at\" : \"2015-01-15T12:39:22Z\",\n    \"added_by\" : {\n      \"external_urls\" : {\n        \"spotify\" : \"http://open.spotify.com/user/jmperezperez\"\n      },\n      \"href\" : \"https://api.spotify.com/v1/users/jmperezperez\",\n      \"id\" : \"jmperezperez\",\n      \"type\" : \"user\",\n      \"uri\" : \"spotify:user:jmperezperez\"\n    },\n    \"is_local\" : false,\n    \"track\" : {\n      \"album\" : {\n        \"album_type\" : \"album\",\n        \"external_urls\" : {\n          \"spotify\" : \"https://open.spotify.com/album/2pANdqPvxInB0YvcDiw4ko\"\n        },\n        \"href\" : \"https://api.spotify.com/v1/albums/2pANdqPvxInB0YvcDiw4ko\",\n        \"id\" : \"2pANdqPvxInB0YvcDiw4ko\",\n        \"images\" : [ {\n          \"height\" : 640,\n          \"url\" : \"https://i.scdn.co/image/599c4ead3874e1e66368e8cf3721b9f79116b328\",\n          \"width\" : 640\n        }, {\n          \"height\" : 300,\n          \"url\" : \"https://i.scdn.co/image/6f31225bf642a58f29a80ca51769c8c588cccdee\",\n          \"width\" : 300\n        }, {\n          \"height\" : 64,\n          \"url\" : \"https://i.scdn.co/image/1a8532495d73b657d592196bfdd9cb980f61443f\",\n          \"width\" : 64\n        } ],\n        \"name\" : \"Progressive Psy Trance Picks Vol.8\",\n        \"type\" : \"album\",\n        \"uri\" : \"spotify:album:2pANdqPvxInB0YvcDiw4ko\"\n      },\n      \"artists\" : [ {\n        \"external_urls\" : {\n          \"spotify\" : \"https://open.spotify.com/artist/6eSdhw46riw2OUHgMwR8B5\"\n        },\n        \"href\" : \"https://api.spotify.com/v1/artists/6eSdhw46riw2OUHgMwR8B5\",\n        \"id\" : \"6eSdhw46riw2OUHgMwR8B5\",\n        \"name\" : \"Odiseo\",\n        \"type\" : \"artist\",\n        \"uri\" : \"spotify:artist:6eSdhw46riw2OUHgMwR8B5\"\n      } ],\n      \"disc_number\" : 1,\n      \"duration_ms\" : 376000,\n      \"explicit\" : false,\n      \"external_ids\" : {\n        \"isrc\" : \"DEKC41200989\"\n      },\n      \"external_urls\" : {\n        \"spotify\" : \"https://open.spotify.com/track/4rzfv0JLZfVhOhbSQ8o5jZ\"\n      },\n      \"href\" : \"https://api.spotify.com/v1/tracks/4rzfv0JLZfVhOhbSQ8o5jZ\",\n      \"id\" : \"4rzfv0JLZfVhOhbSQ8o5jZ\",\n      \"is_playable\" : true,\n      \"name\" : \"Api\",\n      \"popularity\" : 8,\n      \"preview_url\" : \"https://p.scdn.co/mp3-preview/c440fa9ff320fb74629f8477bff45b1a377897ab\",\n      \"track_number\" : 10,\n      \"type\" : \"track\",\n      \"uri\" : \"spotify:track:4rzfv0JLZfVhOhbSQ8o5jZ\"\n    }\n  } ],\n  \"limit\" : 100,\n  \"next\" : null,\n  \"offset\" : 0,\n  \"previous\" : null,\n  \"total\" : 1\n}" true))

(defn double-items [items] (conj items (first items)))

(defn get-items [struct] (struct :items))

(defn update-items [struct] (assoc struct :items (double-items (get-items struct))))

;; real function calling mocks
(defn tracks [id] spotify-playlist-tracks-stub)

;(defn add-track [track] (str (update-items spotify-playlist-tracks-stub)))
(defn add-track [track] (str track))
