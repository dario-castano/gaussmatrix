(ns gaussmatrix.core
  (:require [gaussmatrix.matrix :as mx])
  (:gen-class))

(defn -main
  "Crea una lista de listas, genera la matriz y resuelve"
  [& args]
  (let [example-list [[1 2 -1 -1 0] 
                      [2 -1 2 3 7] 
                      [-1 3 3 -2 17] 
                      [3 -2 1 1 -2]]
        matrix (mx/gen-matrix example-list)
        solved-matrix (mx/solve matrix)]
    
    (println solved-matrix)
    (mx/gen-matrix solved-matrix)))
