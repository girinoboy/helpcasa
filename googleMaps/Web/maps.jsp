<!-- 
  Copyright (c) 2008 Google Inc.
  
  You are free to copy and use this sample.
  License can be found here: http://code.google.com/apis/ajaxsearch/faq/#license
-->

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Center Point Control - Google AJAX Search API Sample</title>
    <style type="text/css">
      body *, table *,
      body {
        font-family: "trebuchet ms", verdana, sans-serif;
        font-size: 13px;
      }

      h2 {
        font-size : 14px;
        font-weight : bold;
        background-color : rgb(230, 248, 221);
        border-top : 1px solid rgb(128, 198, 90);
        text-align : center;
        margin-top : 4px;
        margin-bottom : 10px;
      }


      td {
        vertical-align : top;
      }

      td.map {
        width : 600px;
        }

     .mapDiv {
        border: 1px solid #979797;
        height : 200px;
        width : 300px;
        }

      .controls {
        margin-top : 10px;
      }

      div.control {
        margin-bottom : 8px;
      }

      .control input {
        margin-right : 8px;
        color : #676767;
      }

      .control input[type=text] {
        width : 150px;
      }

      .control span {
        color : #676767;
      }

      #search_control { margin-left: 20px; }
    </style>

    <!-- Replace with http://www.google.com/jsapi 
    <script src="../../../jsapi" type="text/javascript"></script>-->
	<script src="http://www.google.com/jsapi?key=ABQIAAAA3MlzePjziJ1uP_37GBnQLBQpAo20Dt-EL8Z0l2hp_6zuYIL7DhTXnkDpPWVPgKo53JPtTaLzlGeccA" type="text/javascript"></script>
    <script language="Javascript" type="text/javascript">//<![CDATA[
      google.load('search', '1');
      google.load('maps', '2');
      
      var app;
      function OnLoad() {
        app = new MyApp();
      }

      function MyApp() {
        // set up for Gmap2/GLatLng Version
        this.controller = new Object();
        this.controller.locationStringInput = document.getElementById("useStringInput1");
        this.controller.mapElement = document.getElementById("mapDiv1");
        this.controller.gmap = new google.maps.Map2(this.controller.mapElement);
        this.controller.gmap.addControl(new google.maps.SmallMapControl());
        this.controller.gmap.setCenter(new google.maps.LatLng(32.833442, -96.915893), 11);

        // create a search control
        this.controller.searchControl = new google.search.SearchControl();
        this.controller.localSearch = new google.search.LocalSearch();
        this.controller.searchControl.addSearcher(this.controller.localSearch);

        // tell the searcher to draw itself and tell it where to attach
        this.controller.localSearch.setCenterPoint(this.controller.gmap.getCenter());
        this.controller.searchControl.draw(document.getElementById("search_control1"));

        // execute an inital search
        this.controller.searchControl.execute("Starbucks Coffee");

      }

      MyApp.prototype.useMap = function() {
        this.controller.localSearch.setCenterPoint(this.controller.gmap);
        this.controller.searchControl.execute(null);
      }

      MyApp.prototype.usePoint = function() {
        this.controller.localSearch.setCenterPoint(this.controller.gmap.getCenter());
        this.controller.searchControl.execute(null);
      }

      MyApp.prototype.useString = function() {
        this.controller.localSearch.setCenterPoint(this.controller.locationStringInput.value);
        this.controller.searchControl.execute(null);
      }
      google.setOnLoadCallback(OnLoad, true);
    //]]>
    </script>
  </head>

  <body>
    <div style="text-align: right; color=#676767; margin-bottom: 8px;">
      <a href="http://code.google.com/apis/ajaxsearch/index.html"><span style="color:#676767; cursor: pointer; text-decoration: underline;">Google AJAX Search API Documentation</span></a>
    </div>
      <h2>Using GMap2/GLatLng</h2>

      <table>
        <td class="map">
          <div class="mapDiv" id="mapDiv1"></div>
          <div>
            <div class="control">
              <input type="button" value="Use Map" onclick="app.useMap()"></input>
              <span>Use this map to scope your local searches</span>
            </div>

            <div class="control">
              <input type="button" value="Snapshot Map" onclick="app.usePoint()"></input>
              <span>Snapshot the current center point of this map to scope your local searches</span>
            </div>
            <div class="control">
              <input id="useStringInput1" type="text" value="Santa Barbara, Ca"></input>
              <input type="button" value="Use String" onclick="app.useString()"></input>
              <span>Snapshot this location to scope your local searches</span>

            </div>
          </div>
        </td>
        <td class="searchControl">
          <div id="search_control1"/>
        </td>
      </table>
  </body>
</html>

