(ns nataliedee.routes.archive
  (:require [compojure.core :refer :all]
            [nataliedee.views.layout :as layout]
            [hiccup.element :refer [image unordered-list]])
  (:use net.cgrand.enlive-html)
  (:import java.net.URL))

(defn -get-month-comics-ua []
  (with-open [inputstream (-> (java.net.URL. "http://www.nataliedee.com/index.php")
                            .openConnection
                            (doto (.setRequestProperty "User-Agent"
                                                       "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36"))
                            .getContent)]

  (html-resource inputstream)))

(defn get-month-comics [year month]
  (-> (-get-month-comics-ua)
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

    [:div.images (unordered-list (nd-template year month))]))

(defroutes archive-routes
  (GET "/archives/" [] (pick-month))
  (GET "/archives/:year/:month/" [year month] (render-month year month)))

