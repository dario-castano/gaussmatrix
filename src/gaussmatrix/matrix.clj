(ns gaussmatrix.matrix)

(defn square-matrix?
  "Checks if the matrix has the same height and width"
  [matrix]
  (every? #(= (count matrix) %) (map count matrix)))

(defn solve 
  "Solve the matrix using Gaussian reduction"
  [matrix])