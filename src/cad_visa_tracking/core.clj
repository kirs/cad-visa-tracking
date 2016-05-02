(ns cad-visa-tracking.core
  (:require [cad-visa-tracking.vfs :as vfs]
            [cad-visa-tracking.views :as views]
            [clj-time.core :as time]
            [clj-time.format :as time-format]
            [clj-time.local :as l]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.adapter.jetty :as ring]
            [ring.util.response :refer [redirect response]])
  (:gen-class))

(defroutes app
  (GET "/" [] (views/front)))

(defn -main
  [& args]
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "5000"))]
    (ring/run-jetty app {:port port})))
