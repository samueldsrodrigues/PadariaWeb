/*!
 * Brinquedoteca
 * arquivo com funções peculiares ao sistema
 */

/**
 * Função para remover caracteres de um determinado campo de texto
 * @param e - event 
 */
function soNumeros(e) {
	if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
		return false;
	}
}

function select2() {
	$(".chzn-select").select2();
}

/**
 * Abrevia as informações do tempo de sincronização na versão mobile
 */
$(document).ready(function() {
	$.eMobile = (/android|webos|iphone|ipad|ipod|blackberry|iemobile|opera mini/i.test(navigator.userAgent.toLowerCase()));
	if($.eMobile){
		console.log('is mobile');
		$('#infSync').hide();
		$('#infSyncMobile').show();
		
		$('#layout-desktop').hide();
		
		$('#header-mobile').show();
		$('#header-desktop').hide();
		
		$('#painelDados').css('padding-top', '70px');
	}else{
		console.log('isnnot mobile');
		$('#infSync').show();
		$('#infSyncMobile').hide();
		
		$('#layout-desktop').show();
		
		$('#header-mobile').hide();
		$('#header-desktop').show();
	}
});