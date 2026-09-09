(ns clojure-noob.check)

;; into
(def scourge (map identity {:sunlight-reaction "Glitter!"}))
;; this will be a `lazy-seq`
scourge
;; lets make this into a map again
(into {} scourge)
