(ns nataliedee.views.layout
  (:require [hiccup.page :refer [html5 include-css include-js]]))

(defn common [& body]
  (html5
    [:head
     [:title "Natalie Dee Mobile"]
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1.0"}]
     [:link {:rel "apple-touch-icon" :href "/img/ios-icon.png"}]

     (include-css "/css/screen.css")
     (include-js  "/js/jquery.js")
     (include-js  "/js/site.js")]
    [:body
      [:header.navbar.navbar-inverse.navbar-fixed-top.bs-docs-nav
        [:div.container
          [:div.navbar-header
            [:a.navbar-brand {:href "/"}"NATALIE DEE"]]]]
      [:footer
        [:div.container
          [:div.message
            [:center
             [:a.btn.btn-success
                {:href "/"}
                "More Random!" ]
             "&nbsp;&nbsp;&nbsp;"
             [:a.btn.btn-warning
                {:href "/archives/"}
                "Pick a Month" ]]]]]

      body

      [:div.loading.hidden
        [:div.container
          [:div.message
            [:center
              [:p "Loading more... "]
              [:img {:src "/img/loading.gif"}]]]]]

      [:footer
        [:div.container
          [:div.message
            [:center
             [:a.btn.btn-success
                {:href "/"}
                "More Random!" ]
             "&nbsp;&nbsp;&nbsp;"
             [:a.btn.btn-warning
                {:href "/archives/"}
                "Pick a Month" ]]]]]
     ]))
