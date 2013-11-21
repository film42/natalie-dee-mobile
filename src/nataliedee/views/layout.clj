(ns nataliedee.views.layout
  (:require [hiccup.page :refer [html5 include-css]]))

(defn common [& body]
  (html5
    [:head
     [:title "Natalie Dee Mobile"]
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
     (include-css "/css/screen.css")]
    [:body
      [:header.navbar.navbar-inverse.navbar-fixed-top.bs-docs-nav
        [:div.container
          [:div.navbar-header
            [:a.navbar-brand "Natalie Dee Mobile"]]]]

      body

      [:footer
        [:div.container
          [:div.message
            [:center
             [:a.btn.btn-success
                {:onClick "window.location.href=window.location.href"}
                "Tap to load more!" ]]]]]
     ]))
