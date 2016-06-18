# dlink-camera-api

[![Clojars Project](http://clojars.org/org.tiago/dlink-camera-api/latest-version.svg)](http://clojars.org/org.tiago/dlink-camera-api)

[![Build Status](https://travis-ci.org/etiago/dlink-camera-api.svg?branch=master)](https://travis-ci.org/etiago/dlink-camera-api)

Unlike FOSCAM cameras, DLink's don't seem to provide any sort of API to programmatically control its seetings. Bothered by the fact that I couldn't easily enable and disable motion detection via code as to integrate the camera into my home automation setup, I decided to write this library which essentially scrapes the page requests done by DLink's web interface and allows (for now) to enable and disable motion detection.

## Installation

Just add the dependency in your ```project.clj``` and let Leiningen take care of the rest.

## Usage

The usage is simple. Do the following:

```clojure
(:require
   [dlink-camera-api.core :as dlink-api])
   
(dlink-api/do-camera-request
   {:ip "<CAMERA'S_IP_ADDRESS"
    :username "<YOUR_USERNAME>"
    :password "<YOUR_PASSWORD>"
    :form-params
    {:enable "yes"
     :mbmask "00000000000000000000000000000000000000000000000000000000000000FFFFFF0000FFFFFF0000FFFFFF0000FFFFFF0000FFFFFF0000FFFFFF0000FFFFFF0000FFFFFF0000FFFFFF0000FFFFFF0000FFFFFF0000FFFFFF0000FFFFFF0000FFFFFF0000FFFFFF0000FFFFFF0000FFFFFF0000FFFFFF00000000000000000000000000000000000000000000000000000000000000"
     :sensitivity "90"})
```

Keep in mind that the ```:form-params``` value is up to you to define. You can easily figure it out by using Chrome's or Firefox's debug tools and looking at the HTTP requests to the ```/cgi/admin/motion.cgi``` endpoint while you manually enable and disable motion detection in the camera's UI.


## License

Copyright © 2016 Tiago Espinha

Distributed under the MIT License.
