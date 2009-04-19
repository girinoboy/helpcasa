package br.com;

public enum ConstantesENUM {
	CLIENTE_ID(){
		public Long id(){
			return new Long(3);
					
		}
	}
	;
	
	public Long id(){
		return this.id();
	}
	public String descricao(){
		return this.descricao();
	}

}
