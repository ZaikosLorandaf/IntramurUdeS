function refresh() {
    // Recharge la page parente si elle existe
    if (window.opener) {
        window.opener.location.reload();
    }

    // Vider tous les inputs (text, date, time)
    const inputs = document.querySelectorAll('input');
    inputs.forEach(input => {
        if (input.type === 'checkbox' || input.type === 'radio') {
            input.checked = false;
        } else {
            input.value = '';
        }
    });

    // Réactiver toutes les checkbox (si certaines étaient désactivées)
    const checkboxes = document.querySelectorAll('input[type="checkbox"]');
    checkboxes.forEach(cb => cb.disabled = false);
}
