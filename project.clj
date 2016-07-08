(defproject whoami "0.1.0-SNAPSHOT"
  :description "FCC Project WHO-AM-I"
  :url "https://github.com/leordev/fcc-basejump-reqheaderparser-ms"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [http-kit "2.2.0-beta1"]
                 [cheshire "5.6.3"]
                 [environ "1.0.3"]]
  :main ^:skip-aot whoami.core
  :target-path "target/%s"
  :min-lein-version "2.0.0"
  :plugins [[environ/environ.lein "0.3.1"]]
  :hooks [environ.leiningen.hooks]
  :uberjar-name "whoami.jar"
  :profiles {:production {:env {:production true}}})
  ;:profiles {:uberjar {:aot :all}})
