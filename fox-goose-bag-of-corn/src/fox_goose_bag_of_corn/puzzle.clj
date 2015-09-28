(ns fox-goose-bag-of-corn.puzzle)

(def start-pos [[[:fox :goose :corn :you] [:boat] []]])

(defn make-move [state start boat finish]
  (conj state [start boat finish]))

(defn river-crossing-plan []
  (-> start-pos
      (make-move [:fox :corn] [:boat :you :goose] [])
      (make-move [:fox :corn] [:boat] [:you :goose])
      (make-move [:fox :corn] [:boat :you] [:goose])
      (make-move [:fox :corn :you] [:boat] [:goose])
      (make-move [:fox] [:boat :you :corn] [:goose])
      (make-move [:fox] [:boat] [:you :corn :goose])
      (make-move [:fox] [:boat :you :goose] [:corn])
      (make-move [:fox :goose :you] [:boat] [:corn])
      (make-move [:goose] [:boat :you :fox] [:corn])
      (make-move [:goose] [:boat] [:you :fox :corn])
      (make-move [:goose] [:boat :you] [:fox :corn])
      (make-move [:goose :you] [:boat] [:fox :corn])
      (make-move [] [:boat :you :goose] [:fox :corn])
      (make-move [] [:boat] [:you :goose :fox :corn])))
