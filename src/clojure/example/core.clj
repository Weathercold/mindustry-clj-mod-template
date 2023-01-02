(ns example.core
  (:require [example.util.lambdas :refer [cons1 runnable]])
  (:import (arc Core Events)
           (arc.util CommandHandler Log)
           (mindustry.game EventType$ClientLoadEvent)
           (mindustry.ui.dialogs BaseDialog)))

(defn main []
  (Log/info "Creating example mod.")
  (Events/on
   EventType$ClientLoadEvent
   (cons1
    (fn [_]
      (let [dialog (BaseDialog. "frog")
            cont (.cont dialog)]
        (doto cont
          (-> (.add "behold")
              .row)
          (-> (.image (.find Core/atlas "example-clojure-mod-frog"))
              (.pad 20.)
              .row)
          (-> (.button "I see" ^Runnable (runnable #(.hide dialog)))
              (.size 100., 50.)))
        (.show dialog))))))

(defn init []
  (Log/info "Initializing mod."))

(defn load-content []
  (Log/info "Loading some example content."))

(defn register-client-commands [^CommandHandler handler]
  (Log/info "Registering client commands."))

(defn register-server-commands [^CommandHandler handler]
  (Log/info "Registering server commands."))
