package com.service.dominio;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="despesa")
public class Despesa {
    
	private int idDespesa;	
    private String ano;
    private String mes;
    private String tipoParlamentar;
    private String nome;
    private String tipoDespesa;
    private String cpfCnpj;
    private String fornecedor;
    private String documento;
    private Date data;
    private String descricaoDespesa;
    private BigDecimal valor;

    public Despesa(String ano, String mes, String tipoParlamentar, String nome, String tipoDespesa, String cpfCnpj, 
    		String fornecedor, String documento, Date data, String descricaoDespesa, BigDecimal valor) {
        this.ano = ano;
        this.mes = mes;
        this.tipoParlamentar = tipoParlamentar;
        this.nome = nome;
        this.tipoDespesa = tipoDespesa;
        this.cpfCnpj = cpfCnpj;
        this.fornecedor = fornecedor;
        this.documento = documento;
        this.data = data;
        this.descricaoDespesa = descricaoDespesa;
        this.valor = valor;
    }
    
    public Despesa(){}

    /**
     * @return the ano
     */
    public String getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(String ano) {
        this.ano = ano;
    }

    /**
     * @return the mes
     */
    public String getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(String mes) {
        this.mes = mes;
    }

    /**
     * @return the tipoParlamentar
     */
    @Column(name="tipo_parlamentar")
    public String getTipoParlamentar() {
        return tipoParlamentar;
    }

    /**
     * @param tipoParlamentar the tipoParlamentar to set
     */
    public void setTipoParlamentar(String tipoParlamentar) {
        this.tipoParlamentar = tipoParlamentar;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the tipoDespesa
     */
    @Column(name="tipo_despesa")
    public String getTipoDespesa() {
        return tipoDespesa;
    }

    /**
     * @param tipoDespesa the tipoDespesa to set
     */
    public void setTipoDespesa(String tipoDespesa) {
        this.tipoDespesa = tipoDespesa;
    }

    /**
     * @return the cpfCnpj
     */
    @Column(name="cpf_cnpj")
    public String getCpfCnpj() {
        return cpfCnpj;
    }

    /**
     * @param cpfCnpj the cpfCnpj to set
     */
    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    /**
     * @return the fornecedor
     */
    public String getFornecedor() {
        return fornecedor;
    }

    /**
     * @param fornecedor the fornecedor to set
     */
    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    /**
     * @return the documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * @param documento the documento to set
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the descricaoDespesa
     */
    @Column(name="descricao_despesa", length = 1000)
    public String getDescricaoDespesa() {
        return descricaoDespesa;
    }

    /**
     * @param descricaoDespesa the descricaoDespesa to set
     */
    public void setDescricaoDespesa(String descricaoDespesa) {
        this.descricaoDespesa = descricaoDespesa;
    }

    /**
     * @return the valor
     */
    public BigDecimal getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_despesa")
	private int getIdDespesa() {
		return idDespesa;
	}

	private void setIdDespesa(int idDespesa) {
		this.idDespesa = idDespesa;
	}
    
    
    
}
