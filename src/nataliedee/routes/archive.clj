(ns nataliedee.routes.archive
  (:require [compojure.core :refer :all]
            [nataliedee.views.layout :as layout]
            [hiccup.element :refer [image unordered-list]])
  (:use net.cgrand.enlive-html)
  (:import java.net.URL))

(defn get-month-comics [year month]
  (-> (str "http://www.nataliedee.com/archives/" year "/" month "/") URL. html-resource
    (select [:div.arcDayComic :img.comic])))

(defn nd-template [year month]
  (let [images (vec (get-month-comics year month))]
    (for [i (range (count images))]
      (image (-> (images i) :attrs :src)))))

(defn get-current-year []
  (. (java.util.Calendar/getInstance) get java.util.Calendar/YEAR))

(def months ["Jan" "Feb" "Mar" "Apr" "May" "Jun" "Jul" "Aug" "Sep" "Oct" "Nov" "Dec"])
(def start-year 2008)

(defn generate-archive-url [year month]
  (str "/archives/" year "/" month "/"))

(defn date-picker-template []
  (for [y (range start-year (inc (get-current-year)))]
    [:div.bs-callout.bs-callout-warning
      [:h4 y] ;; For some year

      (for [m (range (count months))]
        (let [year y month (months m)]
        [:a.btn.btn-info.btn-date {:href (generate-archive-url year month)} month]))
      ]))

;; Base
(defn pick-month []
  (layout/common
    [:section
      [:div.container
        [:div.message
          [:center [:h3 "Archives"]]]]]

    (date-picker-template )))

(defn render-month [year month]
  (layout/common
    [:section
      [:div.container
        [:div.message
          [:center [:h3 month " " year]]]]]

    [:div.archived-images (unordered-list (nd-template year month))]))

(defroutes archive-routes
  (GET "/archives/" [] (pick-month))
  (GET "/archives/:year/:month/" [year month] (render-month year month)))

