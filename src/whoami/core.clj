(ns whoami.core
  (:require [org.httpkit.server :as http-kit]
            [cheshire.core :as json]
            [clojure.string :as str]
            [environ.core :refer [env]])
  (:gen-class))

(defn extract-headers-language
  "extract the language from the given request"
  [req]
  (first (str/split ((:headers req) "accept-language") #",")))

(defn extract-headers-software
  "extract the software from the given request"
  [req]
  (str/replace (->> ((:headers req) "user-agent")
                    (re-find #"(\(.+?\))" )
                    (first)) #"\(|\)" ""))

(defn who-am-i
  "reads request and return the who-am-i info"
  [req]
  {:ipaddress (:remote-addr req)
   :language  (extract-headers-language req)
   :software  (extract-headers-software req)})

(defn app
  "Main server Application Response"
  [req]
  {:status  200
   :headers {"Content-Type" "application/json"}
   :body    (json/generate-string (who-am-i req))})

; Detect server port
(def port
  (if-let [port (env :port)]
    (Integer. port)
    8080))

(defn -main
  "Start Server"
  [& args]
  (http-kit/run-server app {:port port})
  (println (str "Running server on port " port)))