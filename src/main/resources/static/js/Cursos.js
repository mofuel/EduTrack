// Obtener la lista de cursos desde la API y mostrarla en la tabla
function cargarCursos() {
    fetch('/api/cursos')
        .then(response => response.json())
        .then(cursos => {
            let tbody = document.getElementById('cursosTableBody');
            tbody.innerHTML = ''; // Limpiar tabla antes de agregar los nuevos cursos
            cursos.forEach(curso => {
                let tr = document.createElement('tr');
                tr.innerHTML = `
                    <td>${curso.nombre}</td>
                    <td>${curso.descripcion}</td>
                    <td>${curso.docente?.nombre || 'Sin nombre'}</td>
                    <td>
                        <button class="btn btn-warning btn-sm"
                                onclick="abrirModalEditarCurso('${curso.id}', '${curso.nombre}', \`${curso.descripcion}\`, '${curso.docente?.id || ''}')">
                            Editar
                        </button>
                        <button class="btn btn-danger btn-sm"
                                onclick="eliminarCurso('${curso.id}')">
                            Eliminar
                        </button>
                    </td>
                `;
                tbody.appendChild(tr);
            });
        })
        .catch(error => {
            console.error('Error al cargar los cursos:', error);
        });
}

// Función para abrir el modal de edición de curso
function abrirModalEditarCurso(id, nomCurso, descripCurso, docenteId) {
    document.getElementById('editar-curso-id').value = id;
    document.getElementById('editar-nom-curso').value = nomCurso;
    document.getElementById('editar-descrip-curso').value = descripCurso;
    document.getElementById('editar-docente-id').value = docenteId;

    let modal = new bootstrap.Modal(document.getElementById('modalEditarCurso'));
    modal.show();
}

// Función para eliminar un curso
function eliminarCurso(id) {
    if (confirm('¿Estás seguro de que deseas eliminar este curso?')) {
        fetch(`/api/cursos/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al eliminar');
            }
            cargarCursos(); // Actualiza la lista de cursos después de eliminar
        })
        .catch(error => {
            console.error('Error al eliminar:', error);
            alert('Hubo un error al eliminar el curso.');
        });
    }
}

// Función para manejar la edición de curso
document.addEventListener('DOMContentLoaded', function () {
    cargarCursos(); // Cargar los cursos al cargar la página

    document.getElementById('formEditarCurso').addEventListener('submit', function(event) {
        event.preventDefault();

        const id = document.getElementById('editar-curso-id').value;
        const nomCurso = document.getElementById('editar-nom-curso').value;
        const descripCurso = document.getElementById('editar-descrip-curso').value;
        const docenteId = document.getElementById('editar-docente-id').value;

        const datosActualizados = {
            id: id,
            nombre: nomCurso,
            descripcion: descripCurso,
            docente: {
                id: docenteId
            }
        };

        fetch(`/api/cursos/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(datosActualizados)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al actualizar');
            }
            return response.json();
        })
        .then(cursoActualizado => {
            bootstrap.Modal.getInstance(document.getElementById('modalEditarCurso')).hide();
            cargarCursos(); // Actualiza la lista después de la edición
        })
        .catch(error => {
            console.error('Error al actualizar:', error);
            alert('Hubo un error al actualizar el curso.');
        });
    });
});
