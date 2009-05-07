package br.com;

public enum ConstantesENUM {
	ADMINISTRADOR_ID(){
		public Long id(){
			return new Long(1);
					
		}
	},
	ATENDENTE_ID(){
		public Long id(){
			return new Long(2);
					
		}
	},
	CLIENTE_ID(){
		public Long id(){
			return new Long(3);
					
		}
	},
	PROFISSIONAL_ID(){
		public Long id(){
			return new Long(4);
					
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
