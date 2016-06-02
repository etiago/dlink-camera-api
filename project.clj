(defproject org.tiago/dlink-camera-api "0.2.0"
  :description "A screen-scraping tool for interacting with DLink cameras"
  :url "https://github.com/etiago/dlink-camera-api"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [clj-http "2.2.0"]
                 [org.clojure/data.codec "0.1.0"]]
  :main ^:skip-aot dlink-camera-api.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
