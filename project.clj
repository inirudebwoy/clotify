(defproject clotify "0.1.0-SNAPSHOT"
  :plugins [[lein-ring "0.8.11"]]
  :ring {:handler clotify.core/handler}
  :description "Spotify REST client"
  :url "https://github.com/inirudebwoy/clotify"
  :license {:name "GPL-2"
            :distribution :repo}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [liberator "0.13"]
                 [compojure "1.4.0"]
                 [ring/ring-core "1.4.0"]])
