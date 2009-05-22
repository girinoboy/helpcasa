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
	},
	STATUS_SOLICITADO(){
		public Long id(){
			return new Long(1);
					
		}
		public String descricao(){
			return "Solicitado";
		}
	},
	STATUS_CANCELADO(){
		public Long id(){
			return new Long(2);
					
		}
		public String descricao(){
			return "Cancelado";
		}
	},
	STATUS_FINALIZADO(){
		public Long id(){
			return new Long(3);
					
		}
		public String descricao(){
			return "Finalizado";
		}
	},
	STATUS_PAGO(){
		public Long id(){
			return new Long(4);
					
		}
		public String descricao(){
			return "Pago";
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
