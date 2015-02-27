
$(function() {
    $('#default').puipanel();

    $('#options').puipanel({
        toggleable: true
        ,closable: true
    });

    $('#horiztoggle').puipanel({
        toggleable: true
        ,toggleOrientation: 'horizontal'
    });
});
$(function() {
    $('#images').puigalleria({
        panelWidth: 500,
        panelHeight: 313
    });

    $('#customcontent').puigalleria({
        autoPlay: false,
        customContent: true,
        showCaption: false,
        panelWidth: 360,
        panelHeight: 200,
        frameWidth: 100,
        frameHeight: 60,
        effect: 'fold',
        effectSpeed: 500
    });

    $('#doc').puitabview();
});

