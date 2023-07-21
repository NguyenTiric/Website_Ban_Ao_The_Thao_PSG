
// Ready
$(document).ready(function () {

    // Create image loader plugin
    var imagesloader = $('[data-type=imagesloader]').imagesloader({
        minSelect: 3
        ,
        imagesToLoad: [{"Url": "./img/Nespresso001.jpg", "Name": "Nespresso001"}, {
            "Url": "./img/Nespresso002.jpg",
            "Name": "Nespresso002"
        }]
    });

    //Form
    $frm = $('#frm');

    // Form submit
    $frm.submit(function (e) {

            var $form = $(this);

            var files = imagesloader.data('format.imagesloader').AttachmentArray;
            var il = imagesloader.data('format.imagesloader');

            if (il.CheckValidity())
                alert('Upload ' + files.length + ' files');

            e.preventDefault();
            e.stopPropagation();
        }
    );

});