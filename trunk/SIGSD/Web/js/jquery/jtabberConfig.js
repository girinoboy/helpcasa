$(document).ready(function(){
		
		$.jtabber({
			mainLinkTag: "#nav a", // much like a css selector, you must have a 'title' attribute that links to the div id name
			activeLinkClass: "selected", // class that is applied to the tab once it's clicked
			hiddenContentClass: "hiddencontent", // the class of the content you are hiding until the tab is clicked
			showDefaultTab: null, // 1 will open the first tab, 2 will open the second etc.  null will open nothing by default
			showErrors: true, // true/false - if you want errors to be alerted to you
			effect: 'fade', // null, 'slide' or 'fade' - do you want your content to fade in or slide in?
			effectSpeed: 'slow' // 'slow', 'medium' or 'fast' - the speed of the effect
		})
		
	})