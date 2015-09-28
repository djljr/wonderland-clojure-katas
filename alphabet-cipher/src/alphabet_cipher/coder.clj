(ns alphabet-cipher.coder)

(def alphabet "abcdefghijklmnopqrstuvwxyz")

(defn rotate [n coll]
  (vec (concat (drop n coll) (take n coll))))

(def rotate-memo (memoize rotate))

(defn letter->index [c]
  (-> c
      int
      (- (int \a))))

(defn index->letter [idx]
  (-> idx
      (+ (int \a))
      char))

(defn encode [keyword message]
  (let [keyword (take (count message) (cycle keyword))
        km-pairs (map vector keyword message)]
    (apply str (for [[k m] km-pairs
                     :let [k-idx (letter->index k)
                           m-idx (letter->index m)
                           alphabet' (rotate m-idx alphabet)]]
                 (get alphabet' k-idx)))))

(defn decode [keyword cypher]
  (let [keyword (take (count cypher) (cycle keyword))
        kc-pairs (map vector keyword cypher)]
    (apply str (for [[k c] kc-pairs
                     :let [k-idx (letter->index k)
                           c-idx (letter->index c)
                           alphabet' (rotate k-idx alphabet)
                           m-idx (.indexOf alphabet' c)]]
                 (index->letter m-idx)))))

(defn decypher [cypher message]
  (let [cm-pairs (map vector cypher message)]
    (apply str (for [[c m] cm-pairs
                     :let [c-idx (letter->index c)
                           m-idx (letter->index m)
                           alphabet' (rotate m-idx alphabet)
                           k-idx (.indexOf alphabet' c)]]
                 (index->letter k-idx)))))