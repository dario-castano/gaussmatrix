(ns gaussmatrix.core
  (:require [gaussmatrix.matrix :as mx])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [example-list [[1 -2 2 -3] 
                      [3 4 -1 1] 
                      [2 -3 2 -1] 
                      [1 1 -3 -2]]]
    
    (mx/solve example-list)))
