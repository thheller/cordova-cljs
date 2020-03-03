(ns demo.app
  (:require [shadow.dom :as dom]))

(defn ^:dev/after-load start []
  (dom/append [:h2 "start called"]))

(defn init []
  (dom/append [:h1 "init called"])
  (start))
