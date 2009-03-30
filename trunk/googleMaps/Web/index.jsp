<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>My Google AJAX Search API Application</title>
    <script src="http://www.google.com/jsapi?key=ABQIAAAA3MlzePjziJ1uP_37GBnQLBQpAo20Dt-EL8Z0l2hp_6zuYIL7DhTXnkDpPWVPgKo53JPtTaLzlGeccA" type="text/javascript"></script>
    <script language="Javascript" type="text/javascript">
    //<![CDATA[

    google.load("search", "1");
    google.load('maps', '2');
    

    function OnLoad() {
      // Create a search control
      var searchControl = new google.search.SearchControl();

      // Add in a full set of searchers
      var localSearch = new google.search.LocalSearch();
      searchControl.addSearcher(localSearch);
      searchControl.addSearcher(new google.search.WebSearch());
      searchControl.addSearcher(new google.search.VideoSearch());
      searchControl.addSearcher(new google.search.BlogSearch());

      // Set the Local Search center point
      localSearch.setCenterPoint("New York, NY");

      // Tell the searcher to draw itself and tell it where to attach
      searchControl.draw(document.getElementById("searchcontrol"));

      // Execute an inital search
      searchControl.execute("Google");
    }
    google.setOnLoadCallback(OnLoad);
	
	var map;
    var geocoder;

    function load() {
      map = new GMap2(document.getElementById("map"));
      map.setCenter(new GLatLng(23, 48), 14);
      geocoder = new GClientGeocoder();
	  findLocation("lauro linhares, floripa");
    }

    // addAddressToMap() is called when the geocoder returns an
    // answer.  It adds a marker to the map with an open info window
    // showing the nicely formatted version of the address and the country code.
    function addAddressToMap(response) {
      map.clearOverlays();
      if (!response || response.Status.code != 200) {
        alert("Sorry, we were unable to geocode that address");
      } else {
        place = response.Placemark[0];
        point = new GLatLng(place.Point.coordinates[1],
                            place.Point.coordinates[0]);
        marker = new GMarker(point);
        map.addOverlay(marker);
        marker.openInfoWindowHtml(place.address + '<br>' +
          '<b>Country code:</b> ' + place.AddressDetails.Country.CountryNameCode);
      }
    }

    // showLocation() is called when you click on the Search button
    // in the form.  It geocodes the address entered into the form
    // and adds a marker to the map at that location.
    function showLocation() {
      var address = document.forms[0].q.value;
      geocoder.getLocations(address, addAddressToMap);
    }

   // findLocation() is used to enter the sample addresses into the form.
    function findLocation(address) {
      document.forms[0].q.value = address;
      showLocation();
    }

    //]]>
    </script>
    <script type="Javascript">
    
    </script>
  </head>
  <body onload="load();">
    <div id="searchcontrol">Loading...</div>
    <h1>Consulta ao CEP</h1>
    <!-- Creates a simple input box where you can enter an address
         and a Search button that submits the form. //-->
    <form action="#" onsubmit="showLocation(); return false;">
      <p>
        <b>Procure um endereço:</b>
        <input type="text" name="q" value="lauro linhares, floripa" class="address_input" size="40" />
        <input type="submit" name="find" value="Buscar" />

      </p>
    </form>
    <div id="map" style="width: 500px; height: 300px"></div>

  </body>
</html>