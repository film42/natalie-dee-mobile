(ns nataliedee.main
  (:require [jayq.core :as jq])
  (:use [jayq.util :only [log]]
        [jayq.core :only [$]]
        [nataliedee.utils :only [on-scroll scroll-distance]])
  (:use-macros [jayq.macros :only [let-ajax]]))

;; JQuery Holdings
(def $loading ($ ".loading"))
(def $images ($ ".images ul"))
;; Constants
(def path (-> js/window (aget "location") (aget "pathname")))
(def max-scroll 5)
;; Runtime Vars
(def current-scroll-level 0)
(def updating false)

;; Ajax
(defn get-random-page []
  (jq/remove-class $loading :hidden)
  (if (< current-scroll-level max-scroll)
    (let-ajax [p {:url "/random"}]
      (jq/add-class $loading :hidden)
      (def updating false)           ; hack
      (jq/append $images
        (jq/children ($ p))))
    (do
      (jq/text ($ ".loading p") "Want more? Click 'More Random!' below!")
      (jq/add-class ($ ".loading img") :hidden))))

;; Core
(defn infi-scroll []
  (let [d (scroll-distance)]
    (if (and
         (< d 1000)
         (false? updating)
         (= "/" path))
      (do
        (def current-scroll-level
          (inc current-scroll-level)) ; hack
        (def updating true)           ; hack
        (get-random-page)))))

;; Init
(on-scroll ($ js/window) infi-scroll)


