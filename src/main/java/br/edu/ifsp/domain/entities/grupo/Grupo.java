package br.edu.ifsp.domain.entities.grupo;

import br.edu.ifsp.domain.entities.ativo.Ativo;
import br.edu.ifsp.domain.entities.log.LogTransacaoAtivo;
import br.edu.ifsp.domain.entities.usuario.Usuario;
import br.edu.ifsp.domain.usecases.utils.Observer;
import br.edu.ifsp.domain.usecases.utils.Subject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grupo extends Subject implements Observer {
    private String cpfUsuario;
    private int id;
    private String nome;

    private float totalLucrado;
    private float totalInvestido;

    private float lucroPotencial;
    private float valorAtual;
    private float investimentoAtual;

    private TipoGrupoEnum tipoGrupo;

    private List<Ativo> listaAtivos = new ArrayList<>();
    private List<LogTransacaoAtivo> historico = new ArrayList<>();

    public Grupo() {
    }

    public Grupo(String nome, TipoGrupoEnum tipoGrupo) {
        this.nome = nome;
        this.tipoGrupo = tipoGrupo;
    }

    public Grupo(int id, String nome, float totalLucrado, float totalInvestido, float lucroPotencial, float valorAtual, float investimentoAtual, TipoGrupoEnum tipoGrupo) {
        this.id = id;
        this.nome = nome;
        this.totalLucrado = totalLucrado;
        this.totalInvestido = totalInvestido;
        this.lucroPotencial = lucroPotencial;
        this.valorAtual = valorAtual;
        this.investimentoAtual = investimentoAtual;
        this.tipoGrupo = tipoGrupo;
    }

    public Grupo(int id, String nome, float totalLucrado, float totalInvestido, float lucroPotencial, float valorAtual, float investimentoAtual, String tipoGrupo) {
        this.id = id;
        this.nome = nome;
        this.totalLucrado = totalLucrado;
        this.totalInvestido = totalInvestido;
        this.lucroPotencial = lucroPotencial;
        this.valorAtual = valorAtual;
        this.investimentoAtual = investimentoAtual;
        this.tipoGrupo = TipoGrupoEnum.getValueByString(tipoGrupo);
    }

    public Grupo(int id, String nome, Float totalLucrado, Float totalInvestido, Float lucroPotencial, Float valorAtual, Float investimentoAtual, String tipoGrupo, String cpfUsuario) {
        this.id = id;
        this.nome = nome;
        this.totalLucrado = totalLucrado;
        this.totalInvestido = totalInvestido;
        this.lucroPotencial = lucroPotencial;
        this.valorAtual = valorAtual;
        this.investimentoAtual = investimentoAtual;
        this.tipoGrupo = TipoGrupoEnum.getValueByString(tipoGrupo);
        this.cpfUsuario = cpfUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getTotalLucrado() {
        return totalLucrado;
    }

    public float getTotalInvestido() {
        return totalInvestido;
    }

    public float getLucroPotencial() {
        return lucroPotencial;
    }

    public float getValorAtual() {
        return valorAtual;
    }

    public float getInvestimentoAtual() {
        return investimentoAtual;
    }

    public TipoGrupoEnum getTipoGrupo() {
        return tipoGrupo;
    }

    public String getTipoString() {
        return tipoGrupo.getString();
    }

    public void deleteFromObservers() {
        Iterator<Observer> iterator = this.getObserverIterator();
        while(iterator.hasNext()) {
            Observer u = iterator.next();
            if( u instanceof Usuario)
                ((Usuario) u).removeGrupo(this);
        }
    }

    public void addLog(LogTransacaoAtivo logTransacaoAtivo) {
        this.historico.add(logTransacaoAtivo);
    }

    public Iterator<LogTransacaoAtivo> getHistoricIterator() {
        return this.historico.iterator();
    }

    public void addAtivo(Ativo ativo) {
        if(ativo.getClass().getName().equals(this.tipoGrupo.getNomeClasse())) {
            addInvestimento(ativo.getValorTotalAtual());
            ativo.addObserver(this);

            this.listaAtivos.add(ativo);
            this.updateValorAtual();
        } else {
            throw new InvalidTipoAtivoException("Cannot add ativo from different type of grupo.");
        }
    }

    public void removeAtivo(int idx) {
        this.removeAtivo(listaAtivos.get(idx));
    }

    public void removeAtivo(Ativo ativo) {
        if(!this.listaAtivos.remove(ativo)) {
            throw new IllegalArgumentException("Cannot sell ativo thats not added");
        }

        removeInvestimentoAtual(ativo.getValorTotalComprado());
        addLucroTotalHistorico(ativo.getValorTotalAtual() - ativo.getValorTotalComprado());

        this.updateValorAtual();
    }

    public Iterator<Ativo> getIteratorAtivos() {
        return this.listaAtivos.iterator();
    }

    private void updateLucroPotencial() {
        this.lucroPotencial = this.valorAtual - this.investimentoAtual;
    }

    private void updateValorAtual() {
        this.valorAtual = 0;

        for (Ativo a : listaAtivos) {
            this.valorAtual += a.getValorTotalAtual();
        }

        updateLucroPotencial();
        notifyObservers();
    }

    private void addLucroTotalHistorico(float lucro) {
        this.totalLucrado += lucro;
    }

    private void addInvestimento(float investimento) {
        this.investimentoAtual += investimento;
        this.totalInvestido += investimento;
    }

    private void removeInvestimentoAtual(float investimento) {
        this.investimentoAtual -= investimento;
    }

    public boolean isEmpty() {
        return this.listaAtivos.isEmpty();
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", totalLucrado=" + totalLucrado +
                ", totalInvestido=" + totalInvestido +
                ", lucroPotencial=" + lucroPotencial +
                ", valorAtual=" + valorAtual +
                ", investimentoAtual=" + investimentoAtual +
                "} " + super.toString();
    }

    @Override
    public void update(Subject o) {
        this.updateValorAtual();

        if(((Ativo) o).getQuantidade() == 0) {
            this.removeAtivo((Ativo) o);
        }
    }

    public String getCpfUsuario() {
        return cpfUsuario;
    }
}
