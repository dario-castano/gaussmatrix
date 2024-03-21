(ns gaussmatrix.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [example-list [[1 -2 2 -3] 
                      [3 4 -1 1] 
                      [2 -3 2 -1] 
                      [1 1 -3 -2]]]
    
    ))

(defn square-matrix?
  "Checks if the matrix has the same height and width" 
  [matrix]
  (= (count matrix) (map count matrix)))

