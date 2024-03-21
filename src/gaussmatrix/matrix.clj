(ns gaussmatrix.matrix 
  (:require [clojure.core.matrix :as cmx]))

(cmx/set-current-implementation :vectorz)

(defn gen-matrix 
  "Generates a matrix" 
  [coll]
  (cmx/matrix coll))

(defn correct-shape?
  "Checks if the matrix has the same height and width"
  [matrix]
  (= (cmx/row-count matrix) (dec (cmx/column-count matrix))))

(defn invertible? 
  "Checks if the matrix could be inverted" 
  [matrix]
  (not= (cmx/det (cmx/reshape matrix (repeat 2 (cmx/row-count matrix)))) 0))

(defn make-pivot 
  "Make a row of a matrix a pivot turning the target position into a one" 
  [matrix index]
  (let [factor (cmx/mget matrix index index)] 
    (cmx/multiply-row matrix index (/ 1 factor))))

(defn make-reduction
  "Make a reduction subtracting the target row minus the product of the target
   element times the pivot row"
  [matrix row-index row-pivot]
  (let [result (cmx/sub 
                (cmx/get-row matrix row-index) 
                (cmx/mul 
                 (cmx/get-row matrix row-pivot) 
                 (cmx/mget matrix row-index row-pivot)))]
    (cmx/set-row matrix row-index result)))

(defn flip-row-signs
  "Flip the signs of all elements in a row"
  [matrix row]
  (cmx/multiply-row matrix row -1))

(defn rec-gauss "" [matrix row-index cols] ())

(defn run-gauss 
  "" 
  [matrix rows cols]
  (if (empty? rows)
    matrix 
    ((let [[head & tail] rows]
       (when
        (< (cmx/mget matrix head (first cols)) 1)
         ((flip-row-signs matrix head)))
       (make-pivot matrix head)
       ))))

(defn prepare-diagonal 
  "" 
  [matrix diagonal indexes]
  (let [[dg-head & dg-tail] diagonal
        [ix-head & ix-tail] indexes]
    (if (not-any? zero? diagonal) 
      matrix
      (loop [dgs dg-tail
             idx ix-tail] 
        ()
        ))))


(defn solve
  "Solve the matrix using Gaussian reduction"
  [matrix]
  (let [right-shape (correct-shape? matrix)
        is-invertible (invertible? matrix)
        cols (range (cmx/row-count matrix))
        rows (range (cmx/row-count matrix))
        diagonal (cmx/diagonal matrix)]
    (when-not right-shape (throw (RuntimeException. "Matrix must be square")))
    (when-not is-invertible (throw (RuntimeException. "Matrix must be invertible"))) 
    (run-gauss 
     (prepare-diagonal matrix diagonal rows) rows cols)))
