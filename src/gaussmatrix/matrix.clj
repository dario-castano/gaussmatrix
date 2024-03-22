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

(defn flip-pivot-row-signs
  "Flip the sign or a pivot row" 
  [matrix index]
  (if (< (cmx/mget matrix index index) 1) 
    (flip-row-signs matrix index)
    matrix))

(defn set-zeros 
  "" 
  [matrix index cols]
  (if (empty? cols) 
    matrix
    (let [[head & tail] cols]
      (recur (make-reduction matrix head index) index tail))))

(defn run-gauss
  "Runs the main algorithm of the Gauss elimination" 
  [matrix rows cols]
  (if (empty? rows)
    matrix 
    (let [[r_head & r_tail] rows
          [c_head & c_tail] cols]
      (recur (-> matrix 
          (flip-pivot-row-signs r_head) 
          (make-pivot r_head) 
          (set-zeros c_head c_tail)) r_tail c_tail))))

(defn solve
  "Solve the matrix using Gaussian reduction"
  [matrix]
  (let [right-shape (correct-shape? matrix)
        is-invertible (invertible? matrix)
        cols (range (cmx/row-count matrix))
        rows (range (cmx/row-count matrix))]
    (when-not right-shape (throw (RuntimeException. "Matrix must be square")))
    (when-not is-invertible (throw (RuntimeException. "Matrix must be invertible"))) 
    (run-gauss matrix rows cols)))
