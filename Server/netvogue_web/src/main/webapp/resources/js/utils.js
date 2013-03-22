function addresstostring(contactinfo) {
	var str = "";
	if(contactinfo.hasOwnProperty('address') && null != contactinfo.address) {
		str =  contactinfo['address'] + '\n';
	}
	if(contactinfo.hasOwnProperty('city') && null != contactinfo.city) {
    	str +=  contactinfo['city'] + '\n';
	}
	if(contactinfo.hasOwnProperty('zip') && null != contactinfo.zip && 0 != contactinfo.zip) {
    	str +=  contactinfo['zip'];
	}
	return str;
}