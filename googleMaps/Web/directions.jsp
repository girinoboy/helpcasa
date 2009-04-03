<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"  xmlns:v="urn:schemas-microsoft-com:vml">
  <head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>Google Maps JavaScript API Example: Advanced Directions</title>
    <script src=" http://maps.google.com/?file=api&amp;v=2.x&amp;key=ABQIAAAAzr2EBOXUKnm_jVnk0OJI7xSosDVG8KKPE1-m51RBrvYughuyMxQ-i1QfUnH94QxWIa6N4U6MouMmBA"
      type="text/javascript"></script>
    <style type="text/css">
      body {
        font-family: Verdana, Arial, sans serif;
        font-size: 11px;
        margin: 2px;
      }
      table.directions th {
	background-color:#EEEEEE;
      }
	  
      img {
        color: #000000;
      }
    </style>
    <script type="text/javascript">
 
    var map;
    var gdir;
    var geocoder = null;
    var addressMarker;

    function initialize() {
      if (GBrowserIsCompatible()) {      
        map = new GMap2(document.getElementById("map_canvas"));
        gdir = new GDirections(map, document.getElementById("directions"));
        GEvent.addListener(gdir, "load", onGDirectionsLoad);
        GEvent.addListener(gdir, "error", handleErrors);

        setDirections("Planaltina", "Taguatinga", "pt_BR");
      }
    }
    
    function setDirections(fromAddress, toAddress, locale) {
      gdir.load("from: " + fromAddress + " to: " + toAddress,
                { "locale": locale });
                
       alert('dist�ncia total da solicita��o de dire��es (por todas as rotas): '+gdir.getDistance());
	   alert('tempo total da solicita��o de dire��es (por todas as rotas): '+gdir.getDuration());
	   alert('n�mero de entradas geocodificadas dispon�veis no resultado: '+gdir.getNumGeocodes());
	   alert('Numeto de rotas: '+gdir.getNumRoutes());
    }

    function handleErrors(){
	   if (gdir.getStatus().code == G_GEO_UNKNOWN_ADDRESS)
	     alert("No corresponding geographic location could be found for one of the specified addresses. This may be due to the fact that the address is relatively new, or it may be incorrect.\nError code: " + gdir.getStatus().code);
	   else if (gdir.getStatus().code == G_GEO_SERVER_ERROR)
	     alert("A geocoding or directions request could not be successfully processed, yet the exact reason for the failure is not known.\n Error code: " + gdir.getStatus().code);
	   
	   else if (gdir.getStatus().code == G_GEO_MISSING_QUERY)
	     alert("The HTTP q parameter was either missing or had no value. For geocoder requests, this means that an empty address was specified as input. For directions requests, this means that no query was specified in the input.\n Error code: " + gdir.getStatus().code);

	//   else if (gdir.getStatus().code == G_UNAVAILABLE_ADDRESS)  <--- Doc bug... this is either not defined, or Doc is wrong
	//     alert("The geocode for the given address or the route for the given directions query cannot be returned due to legal or contractual reasons.\n Error code: " + gdir.getStatus().code);
	     
	   else if (gdir.getStatus().code == G_GEO_BAD_KEY)
	     alert("The given key is either invalid or does not match the domain for which it was given. \n Error code: " + gdir.getStatus().code);

	   else if (gdir.getStatus().code == G_GEO_BAD_REQUEST)
	     alert("A directions request could not be successfully parsed.\n Error code: " + gdir.getStatus().code);
	    
	   else alert("An unknown error occurred.");
	   
	   
	   
	}

	function onGDirectionsLoad(){ 
      // Use this function to access information about the latest load()
      // results.

      // e.g.
      // document.getElementById("getStatus").innerHTML = gdir.getStatus().code;
	  // and yada yada yada...
	}
    </script>

  </head>
  <body onload="initialize()" onunload="GUnload()">
  
  <h2>Maps API Directions Illustrated</h2>
  <form action="#" onsubmit="setDirections(this.from.value, this.to.value, this.locale.value); return false">

  <table>

   <tr><th align="right">De:&nbsp;</th>

   <td><input type="text" size="25" id="fromAddress" name="from"
     value="Planatina"/></td>
   <th align="right">&nbsp;&nbsp;Para:&nbsp;</th>
   <td align="right"><input type="text" size="25" id="toAddress" name="to"
     value="Taguatinga" /></td></tr>

   <tr><th>Language:&nbsp;</th>
   <td colspan="3">
   <select id="locale" name="locale">
   	<option value="pt" selected="selected">Portugues</option>	
    <option value="en">English</option>
    <option value="fr">French</option>
    <option value="de">German</option>
    <option value="ja">Japanese</option>
    <option value="es">Spanish</option>
    </select>

    <input name="submit" type="submit" value="Como chegar" />

   </td></tr>
   </table>

    
  </form>

    <br/>
    <table class="directions">
    <tr><th>Formatted Directions</th><th>Map</th></tr>

    <tr>
    <td valign="top"><div id="directions" style="width: 275px"></div></td>
    <td valign="top"><div id="map_canvas" style="width: 310px; height: 400px"></div></td>

    </tr>
    </table> 
  </body>
</html>
