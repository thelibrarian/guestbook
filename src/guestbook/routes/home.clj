(ns guestbook.routes.home
  (:require [compojure.core :refer :all]
            [guestbook.views.layout :as layout]))

(defn home [& [name error message]]
  (layout/common
    [:h1 "Guestbook"]
    [:p "Welcome to my guestbook"]
    [:p error]
    (show-guests)
    [:hr]
    [:form
     [:p "Name:"]
     [:input]
     [:p "Message:"]
     [:textarea {:rows 10 :cols 40}]]))

(defn show-guests []
  [:ul.guests
   (for [{:keys [message name timestamp]}
         [{:message "Hello" :name "Bob" :timestamp nil}
          {:message "Foo" :name "Bar" :timestamp nil}]]
     [:li
      [:blockquote message]
      [:p "-" [:cite name]]
      [:time timestamp]])])

(defroutes home-routes
  (GET "/" [] (home)))
