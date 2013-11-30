(ns nataliedee.views.random
  (:require [hiccup.page :refer [html5 include-css]]))

(defn template [& body]
  (html5 body))
