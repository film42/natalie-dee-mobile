(ns nataliedee.routes.home
  (:require [compojure.core :refer :all]
            [nataliedee.views.layout :as layout]
            [hiccup.element :refer [image unordered-list]])
  (:use net.cgrand.enlive-html)
  (:import java.net.URL))

(defn get-random-comics []
  (-> "http://www.nataliedee.com/index.php" URL. html-resource
    (select [:div.arcDayComic :img.comic])))

(defn nd-template []
  (let [images (vec (get-random-comics))]
    (for [i (range (count images))]
      (image (-> (images i) :attrs :src)))))

(defn home []
  (layout/common
    [:div.images (unordered-list (nd-template))]))

(defroutes home-routes
  (GET "/" [] (home)))
