(ns tictactoe.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(defn new-board [n]
    (vec (repeat n (vec (repeat n 0)))))

(defonce app-state
    (atom {:text "Welcome to tictactoe"
           :board (new-board 3)}))

(defn tictactoe []
  [:center
    [:h1 (:text @app-state)]
    (into
    [:svg
      {:view-box "0 0 3 3"
       :width 500
       :height 500}]
      (for [i (range (count (:board @app-state)))
            j (range (count (:board @app-state)))]
        [:rect {:width 0.9
                :height 0.9
                :fill (if (zero? (get-in @app-state [:board i j]))
                    "green"
                    "yellow")
                :x i
                :y j
                :on-click
                (fn rect-click [e]
                    (prn "You clicked me!" i j)
                    (swap! app-state update-in [:board i j] inc))}]))])

(reagent/render-component [tictactoe]
                          (. js/document (getElementById "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
)
