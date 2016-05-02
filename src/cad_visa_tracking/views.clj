(ns cad-visa-tracking.views
  (:require [hiccup.page :as h]
            [cad-visa-tracking.vfs :as vfs]
            [clojure.string :as string])
  (:use [hickory.core])
  (:gen-class))

(defn front
  []
  (h/html5
    [:head
      [:title "Hello world"]]
    [:body
      [:div {:id "login"}
        (vfs/get-visa-status "mytrackingnumber" "mypassword")]]))
