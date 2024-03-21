(ns gaussmatrix.core
  (:require [gaussmatrix.matrix :as mx])
  (:gen-class))

(defn -main
  "Crea una lista de listas, genera la matriz y resuelve"
  [& args]
  (let [example-list [[1 -2 2 -3] 
                      [3 4 -1 1] 
                      [2 -3 2 -1] 
                      [1 1 -3 -2]]
        matrix (mx/gen-matrix example-list)]
    
    (println (mx/solve matrix))))
