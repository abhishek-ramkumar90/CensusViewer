function sortObjectsByKey(objects, key){
	objects.sort(function() {
		return function(a, b){
			var objectIDA = a[key];
			var objectIDB = b[key];
			if (objectIDA === objectIDB) {
				return 0;
			}
			return objectIDA > objectIDB ? 1 : -1;        
		};
	}());
}//end of sorting function
