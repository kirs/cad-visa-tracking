(defproject cad-visa-tracking "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main ^:skip-aot cad-visa-tracking.core
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [clj-http "1.1.2"]
                 [clj-time "0.9.0"]
                 [hickory "0.5.4"]
                 [compojure "1.3.4"]
                 [ring "1.4.0-RC1"]]
  :min-lein-version "2.0.0"
  :uberjar-name "cad-visa-tracking.jar"
  :plugins [[lein-ring "0.9.6"]])
