$(document).ready(function() {
    // выбор
    $(document).on('click', '.scroll-area', function() {
        $('.scroll-area').removeClass('selected');
        $(this).addClass('selected');
    });
    //скачивание выбранного файла
    $(document).on('click', '#downloadBtn', function() {
        var id = $('.scroll-area.selected').attr('data-value');

        if (id) {
            window.location.href = '/files/' + id;
        } else {
            toastr.info('Вы не выбрали файл');
        }
    });
    //загрузка на сервер файла
    $('#uploadBtn').click(function() {
        var fileInput = document.getElementById('fileInput');
        var file = fileInput.files[0];
        var formData = new FormData();
        formData.append('file', file);

        $.ajax({
            url: '/files',
            type: 'POST',
            data: formData,
            processData: false,
            contentType: false,
            success: function(response) {
                toastr.success('Файл успешно загружен');
                insertFIleInList(response, file.name);
            },
            error: function(xhr, status, error) {
                toastr.error(error);
            }
        });
    });

});

function insertFIleInList(id, fileName){
    var newScrollArea = $('<div class="scroll-area"></div>');
    newScrollArea.attr('data-value', id);
    newScrollArea.text("Имя файла: " + fileName + "\n ID:" + id);
    $('.scroll-container').append(newScrollArea);
}

$(document).ready(function() {
    $.ajax({
        url: '/files',
        method: 'GET',
        success: function(data) {
            data.forEach(function(file) {
                insertFIleInList(file.id, file.fileName);
            });
        },
        error: function(xhr, status, error) {
            toastr.error(error);
        }
    });
});