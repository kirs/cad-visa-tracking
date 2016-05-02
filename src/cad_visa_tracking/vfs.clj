(ns cad-visa-tracking.vfs
  (:require [clj-http.client :as client]
            [hickory.select :as s]
            [clojure.string :as string])
  (:use [hickory.core])
  (:gen-class))

(def base-url
  "https://www.vfsglobal.ca/CIC-Global-Tracking-Zone2/")

(def form-page-url
  (str
    base-url
   "TrackingParam.aspx?P=221$7$44$21$227$195$228$120$228$113$154$114$232$20$203$67$46$76$241$129$92$213$66$207$78$38$204$51$186$171$81$20"))

(defn fetch-form-page
  []
  (client/get form-page-url))

(def site-htree (-> (fetch-form-page) :body parse as-hickory))

(defn form-url
  []
  (str
    base-url
    (-> (s/select
          (s/child
            (s/tag :form)) site-htree) first :attrs :action)))

(defn get-tag-value-by-id
  [id]
  (-> (s/select
        (s/child
          (s/id id)) site-htree) first :attrs :value))

(defn get-event-validation-value
  []
  (get-tag-value-by-id "__EVENTVALIDATION"))

(defn get-csrf-token
  []
  (get-tag-value-by-id "ct100_hidCSRF"))

(defn get-viewstate
  []
  (get-tag-value-by-id "__VIEWSTATE"))

(defn get-status-page
  [tracking-id date]
  (client/post (form-url)
                {:form-params {
                            "ctl00$CPH$txtTrackingId" tracking-id
                            "ctl00$CPH$txtDOB$txtDate" date
                            "ctl00$hidCSRF" (get-csrf-token)
                            "ctl00$CPH$btnDOB" "Submit"
                            "__EVENTVALIDATION" (get-event-validation-value)
                            "__VIEWSTATE" (get-viewstate)
                            }}))

(defn status-hpage
  [page]
  (as-hickory (parse (:body page))))

(defn parse-visa-status
  [page]
  (-> (s/select
        (s/child
          (s/class "fnstatus")
          (s/tag :table)
          (s/tag :tbody)
          (s/nth-child 1)
          (s/tag :td)) (status-hpage page)) first :content first))

(defn get-visa-status
  [tracking-id date]
  (string/trim
    (parse-visa-status
      (get-status-page tracking-id date))))
