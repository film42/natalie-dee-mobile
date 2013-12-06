(ns nataliedee.utils
  (:require [jayq.core :as jq]))

;; JQuery Holdings
(def $window (jq/$ js/window))
(def $document (jq/$ js/document))

(defn on-scroll [$elem fun]
  (.scroll $elem fun))

(defn scroll-distance []
  (- (jq/height $document) (jq/scroll-top $window)))
