$(document).ready(function () {
   $('.formSearching').on('submit', function (e) {
      e.preventDefault();
      // console.log($('#SearchMess').val());
      let textSearching = $('#SearchMess').val();
      progMuvies(textSearching);
   });
});

function progMuvies(textSearching) {
   // console.log(textSearching);
   axios.get('http://www.omdbapi.com/?s=' + textSearching + '&apikey=4bef165d').then((response) => {
      // console.log(response);
      let films = response.data.Search;
      // console.log(films);
      let result = '';
      $.each(films, (index, film) => { 
         result += `
            <div class="col-md-3">
               <div class="scr text-canter">
                  <img src="${film.Poster}">
                  <h4>${film.Title}</h4>
                  <a onclick="filmSelected('${film.imdbID}')" class="btn btn-primary" href="#">Click to show dateils frfilm</a>
               </div><br>
            </div><br>
         `;
      });
      $('#filmSearch').html(result);
   }).catch((err) => {
      console.log(err);
   });
};

function filmSelected(id) {
   console.log('OK');
   sessionStorage.setItem('FilmID', id);
   window.location = 'searchMovie';
   return false;
}

function progMuvie() {
   let FilmID = sessionStorage.getItem('FilmID');
   axios.get('http://www.omdbapi.com/?i=' + FilmID + '&apikey=4bef165d').then((response) => {
      console.log(response);
      let film = response.data;
      let result = `
      <br>
      <div class="row">
         <div class="col-lg-4">
            <img src="${film.Poster}" class="thumbnail">
         </div>

         <div class="col-lg-8">
            <h2>${film.Title}</h2>
            <ul class="list-group">

               <li class="list-group-item d-flex justify-content-between align-items-center text-primary"><strong>Жанр:</strong> ${film.Genre}</li>
               
               <li class="list-group-item d-flex justify-content-between align-items-center text-primary"><strong>Реліз:</strong> ${film.Released}</li>
               
               <li class="list-group-item d-flex justify-content-between align-items-center text-primary"><strong>Режисер:</strong> ${film.Director}</li>
               
               <li class="list-group-item d-flex justify-content-between align-items-center text-primary"><strong>Письменник:</strong> ${film.Writer}</li>
               
               <li class="list-group-item d-flex justify-content-between align-items-center text-primary"><strong>Час:</strong> ${film.Runtime}</li>
               
               <li class="list-group-item d-flex justify-content-between align-items-center text-primary"><strong>Актори:</strong> ${film.Actors}</li>
            </ul>
         </div>
      </div>
      <div class="row">
         <div class="scr">
         <br>
            <h3>Сюжет</h3>
            <section class="text-primary">${film.Plot}</section>
            
            <hr>
            <a href="https://www.imdb.com/title/${FilmID}" target="_blank" class="btn btn-primary mb-3">Показати на imdb.com</a>
         </div>
      </div>
      `;
      $('#filmSearch').html(result);
   }).catch((err) => {
      console.log(err);
   });
};
