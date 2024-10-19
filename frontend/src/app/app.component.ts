import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { GraficaComponent } from './components/grafica/grafica.component';  // Asegúrate de tener el import del componente gráfico

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, GraficaComponent],  // Importas los componentes necesarios
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
}
