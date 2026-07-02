import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { Livre } from '../../model/livre';
import { LivreService } from '../../service/livre-service';

@Component({
  selector: 'app-livre-page',
  imports: [ CommonModule, ReactiveFormsModule ],
  templateUrl: './livre-page.html',
  styleUrl: './livre-page.css',
})
export class LivrePage implements OnInit {
  private titleService: Title = inject(Title);
  private livreService: LivreService = inject(LivreService);

  private refresh$: Subject<void> = new Subject<void>();
  protected livres$!: Observable<Livre[]>;

  // Reactive Forms
  private formBuilder: FormBuilder = inject(FormBuilder);
  protected formLivre!: FormGroup;
  protected formCtrlTitre!: FormControl;
  protected formCtrlResume!: FormControl;
  protected formCtrlAnnee!: FormControl;
  protected editingLivreId: number | undefined = 0;

  ngOnInit(): void {
    this.titleService.setTitle("Liste des matières");

    this.livres$ = this.refresh$.pipe(
      startWith(0), // Initialisation => forcer le chargement une première fois
      switchMap(() => this.livreService.findAll()) // Transformer au moment du next()
    );

    // Reactive Form
    this.formCtrlTitre = this.formBuilder.control('', Validators.required);
    this.formCtrlResume = this.formBuilder.control('', Validators.required);
    this.formCtrlAnnee = this.formBuilder.control(2000, Validators.required);

    this.formLivre = this.formBuilder.group({
      // Ajout des différents contrôles == input, select, etc.
      // libelle: this.formBuilder.control('', Validators.required)
      titre: this.formCtrlTitre,
      resume: this.formCtrlResume,
      annee: this.formCtrlAnnee
    });
  }

  private reload() {
    this.refresh$.next();
  }

  protected addOrUpdate() {
    // const livre: Livre = {
    //   libelle: this.formCtrlLibelle.value
    // };

    const livre: Livre = this.formLivre.getRawValue();

    if (this.editingLivreId) {
      livre.id = this.editingLivreId;
      this.livreService.update(livre).subscribe(() => this.reload());
    }

    else {
      this.livreService.add(livre).subscribe(() => this.reload());
    }

    this.formLivre.reset();
    this.editingLivreId = 0;
  }

  protected edit(livre: Livre) {
    this.editingLivreId = livre.id;
    this.formCtrlTitre.setValue(livre.titre);
    this.formCtrlResume.setValue(livre.resume);
    this.formCtrlAnnee.setValue(livre.annee);
  }

  protected remove(livre: Livre) {
    this.livreService.remove(livre).subscribe(() => this.reload());
  }
}
